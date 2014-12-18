package com.example.fragmentbestpractice;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class NewsTitleFragment extends Fragment {

	private ListView newsTitleListView;
	private List<News> newsList;
	private NewsAdapter adapter;
	private boolean isTwoPane;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		newsList = getNews();
		adapter = new NewsAdapter(activity, R.layout.news_item, newsList);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.news_title_frag, container,false);
		newsTitleListView = (ListView) view.findViewById(R.id.news_title_list_view);
		newsTitleListView.setAdapter(adapter);
		newsTitleListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				News news = newsList.get(position);
				if(isTwoPane){
					NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
					newsContentFragment.refresh(news.getTitle(), news.getContent());
				}else{
					NewsContentActivity.actionStart(getActivity(), news.getTitle(),news.getContent());
				}
				
			}
		});
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		if(getActivity().findViewById(R.id.news_content_layout) != null){
			isTwoPane = true;
		}else{
			isTwoPane = false;
		}
		
	}

	private List<News> getNews() {
		// TODO Auto-generated method stub
		List<News> newsList = new ArrayList<News>();
		News news1 = new News();
		news1.setTitle("��ӭ���Ϊ�����һ������.   �Ȿ������Git�����ĺܶྫ��,  ��Ŀ�ľ��ǰ����㾡�������Git.");
		news1.setContent("�������ñ����е�ʲô�����Ѿ���ʱ,  �����иĽ�����;  ������Ե������:  ��β���  , Ҳ����ֱ�Ӻ�  ����  ��ϵ. ");
		newsList.add(news1);
		
		News news2 = new News();
		news2.setTitle("�ںϲ������еõ������ͻ��Э��");
		news2.setContent("����һ��, �����ǽ����ͻ֮��, �õ����ύ��������������һ�����ύ: һ�����ύ���ΪHEAD, Ҳ���ǵ�ǰ��֧��tip; ����һ�����ύ���Ϊ��һ��֧��tip, ����ʱ����MERGE_HEAD."
				+ "���diff�����ʾ��file.txt�ڹ�����, �ݴ�2���ݴ�3�еĲ���. git����ÿ��ǰ����ϵ���'+'����'-', �෴��, ��ʹ������ȥ��ʾ����: ��һ��������ʾ��һ�����ύ�빤��Ŀ¼�ļ������Ĳ���,"
				+ "�ڶ���������ʾ�ڶ������ύ�빤���ļ������Ĳ���. (�μ�git diff-files�е�COMBINED DIFF FORMATȡ�ô˸�ʽ��ϸ��Ϣ.");
		newsList.add(news2);
		
		return newsList;
	}

	
}
