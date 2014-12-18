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
		news1.setTitle("欢迎你成为本书的一名读者.   这本书汇聚了Git社区的很多精华,  其目的就是帮助你尽快的掌握Git.");
		news1.setContent("如果你觉得本书中的什么内容已经过时,  或者有改进建议;  那你可以点击这里:  如何参与  , 也可以直接和  译者  联系. ");
		newsList.add(news1);
		
		News news2 = new News();
		news2.setTitle("在合并过程中得到解决冲突的协助");
		news2.setContent("回忆一下, 在我们解决冲突之后, 得到的提交会有两个而不是一个父提交: 一个父提交会成为HEAD, 也就是当前分支的tip; 另外一个父提交会成为另一分支的tip, 被暂时存在MERGE_HEAD."
				+ "面的diff结果显示了file.txt在工作树, 暂存2和暂存3中的差异. git不在每行前面加上单个'+'或者'-', 相反地, 它使用两栏去显示差异: 第一栏用于显示第一个父提交与工作目录文件拷贝的差异,"
				+ "第二栏用于显示第二个父提交与工作文件拷贝的差异. (参见git diff-files中的COMBINED DIFF FORMAT取得此格式详细信息.");
		newsList.add(news2);
		
		return newsList;
	}

	
}
